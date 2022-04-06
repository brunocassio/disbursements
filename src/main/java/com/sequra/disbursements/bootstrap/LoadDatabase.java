package com.sequra.disbursements.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequra.disbursements.repository.MerchantRepository;
import com.sequra.disbursements.repository.OrderRepository;
import com.sequra.disbursements.repository.ShopperRepository;
import com.sequra.disbursements.domain.Merchant;
import com.sequra.disbursements.domain.Order;
import com.sequra.disbursements.dto.OrderDTO;
import com.sequra.disbursements.domain.Shopper;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class responsible for processing the data from the json files (resources/json/...) and insert them into database when the
 * application runs for the first time.
 */
@Component
public class LoadDatabase implements ApplicationListener<ContextRefreshedEvent> {

    private final OrderRepository orderRepository;

    private final MerchantRepository merchantRepository;

    private final ShopperRepository shopperRepository;

    @Value( "${json.orders}" )
    private String jsonOrders;

    @Value( "${json.merchants}" )
    private String jsonMerchants;

    @Value( "${json.shoppers}" )
    private String jsonShoppers;

    private static final Logger logger = Logger.getLogger(LoadDatabase.class.getName());

    public LoadDatabase(OrderRepository orderRepository, MerchantRepository merchantRepository, ShopperRepository shopperRepository) {
        this.orderRepository = orderRepository;
        this.merchantRepository = merchantRepository;
        this.shopperRepository = shopperRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Shopper> shoppers = getShoppersFromJsonFile();
        List<Merchant> merchants = getMerchantsFromJsonFile();
        List<OrderDTO> orders = getOrdersFromJsonFile();
        List<Order> ordersToBeSaved = new ArrayList<>();

        for (OrderDTO orderDTO : orders) {
            Shopper shopper = shoppers.stream()
                    .filter(sp -> orderDTO.getShopper_id().equals(sp.getId().toString()))
                    .findAny()
                    .orElse(null);
            shopper = shopperRepository.save(shopper);

            Merchant merchant = merchants.stream()
                    .filter(mc -> orderDTO.getMerchant_id().equals(mc.getId().toString()))
                    .findAny()
                    .orElse(null);
            merchant = merchantRepository.save(merchant);

            Date createdAt = new Date(orderDTO.getCreatedAt());
            Date completedAt = orderDTO.getCompletedAt().equals("") ? null : new Date(orderDTO.getCompletedAt());
            Order order = new Order(null, merchant, shopper, Double.parseDouble(orderDTO.getAmount()), createdAt, completedAt);
            ordersToBeSaved.add(order);
        }
        logger.info("saving all orders into the database...");
        orderRepository.saveAll(ordersToBeSaved);
        logger.info("All Orders has been saved!");
    }

    private List<OrderDTO> getOrdersFromJsonFile() {
        List<OrderDTO> orders = new ArrayList<>();
        OrderDTO orderDTO;
        try {
            String jsonString = FileUtils.readFileToString(new File(jsonOrders), StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i = 0; i < jsonArray.length(); i++){
                orderDTO = new OrderDTO();
                orderDTO.setId(jsonArray.getJSONObject(i).getString("id"));
                orderDTO.setAmount(jsonArray.getJSONObject(i).getString("amount"));
                orderDTO.setCompletedAt(jsonArray.getJSONObject(i).getString("completed_at"));
                orderDTO.setCreatedAt(jsonArray.getJSONObject(i).getString("created_at"));
                orderDTO.setMerchant_id(jsonArray.getJSONObject(i).getString("merchant_id"));
                orderDTO.setShopper_id(jsonArray.getJSONObject(i).getString("shopper_id"));
                orders.add(orderDTO);
            }

            return orders;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private List<Shopper> getShoppersFromJsonFile() {
        List<Shopper> shoppers = new ArrayList<>();
        try {
            String jsonString = FileUtils.readFileToString(new File(jsonShoppers), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            shoppers = Arrays.asList(mapper.readValue(jsonString, Shopper[].class));
            return shoppers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shoppers;
    }

    private List<Merchant> getMerchantsFromJsonFile() {
        List<Merchant> merchants = new ArrayList<>();
        try {
            String jsonString = FileUtils.readFileToString(new File(jsonMerchants), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            merchants = Arrays.asList(mapper.readValue(jsonString, Merchant[].class));
            return merchants;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return merchants;
    }
}
