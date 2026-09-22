package factory;

import controller.FoodDeliveryController;
import repository.FoodDeliveryRepository;
import service.FoodDeliveryService;

public class FoodDeliveryFactory {

    private FoodDeliveryFactory() {
    }

    public static FoodDeliveryController getController() {

        return FoodDeliveryController.getInstance();
    }

    public static FoodDeliveryService getService() {

        return FoodDeliveryService.getInstance();
    }

    public static FoodDeliveryRepository getRepository() {

        return FoodDeliveryRepository.getInstance();
    }
}