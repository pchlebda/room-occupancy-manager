package com.smart.room;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Component
class RoomUsageMaxCalculator implements RoomUsageCalculable {

    private static final BigDecimal PREMIUM_LVL = new BigDecimal(100);

    @Override
    public RoomsResponse calculate(RoomsRequest roomsRequest, List<Guest> guests) {
        int availableEconomyRooms = roomsRequest.getFreeEconomyRooms();
        int availablePremiumRooms = roomsRequest.getFreePremiumRooms();

        int willingToPayForPremium = calculateWillingToPayForPremium(guests);
        int willingToPayForEconomy = calculateWillingToPayForEconomy(guests);

        int economyUsage = calculateEconomyUsage(availableEconomyRooms, willingToPayForEconomy);
        int numberOfEconomyToUpgrade = calculateNumberOfEconomyToUpgrade(availableEconomyRooms, availablePremiumRooms, willingToPayForEconomy, willingToPayForPremium);
        int premiumUsage = calculatePremiumUsage(availablePremiumRooms, willingToPayForPremium, numberOfEconomyToUpgrade);
        BigDecimal economyTotalValue = calculateEconomyTotalValue(economyUsage, guests, numberOfEconomyToUpgrade);
        BigDecimal premiumTotalValue = calculatePremiumTotalValue(premiumUsage, guests);

        return RoomsResponse.builder()
                .economyRoomUsage(economyUsage)
                .premiumRoomUsage(premiumUsage)
                .economyRoomTotalValue(economyTotalValue)
                .premiumRoomTotalValue(premiumTotalValue)
                .build();
    }

    private int calculateNumberOfEconomyToUpgrade(int availableEconomyRooms, int availablePremiumRooms, int willingToPayForEconomy, int willingToPayForPremium) {
        if (availableEconomyRooms < willingToPayForEconomy && availablePremiumRooms > willingToPayForPremium) {
            return Math.min(willingToPayForEconomy - availableEconomyRooms, availablePremiumRooms - willingToPayForPremium);
        }
        return 0;
    }

    private int calculatePremiumUsage(int availablePremiumRooms, int willingToPayForPremium, int numberOfEconomyToUpgrade) {
        return Math.min(willingToPayForPremium + numberOfEconomyToUpgrade, availablePremiumRooms);
    }

    private int calculateEconomyUsage(int availableEconomyRooms, int willingToPayForEconomy) {
        return Math.min(willingToPayForEconomy, availableEconomyRooms);
    }

    private BigDecimal calculateEconomyTotalValue(int roomUsage, List<Guest> willingToPayPrices, int numberOfEconomyToUpgrade) {
        return willingToPayPrices.stream()
                .map(Guest::getOfferPrice)
                .filter(price -> price.compareTo(PREMIUM_LVL) < 0)
                .sorted(Comparator.reverseOrder())
                .skip(numberOfEconomyToUpgrade)
                .limit(roomUsage)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculatePremiumTotalValue(int roomUsage, List<Guest> willingToPayPrices) {
        return willingToPayPrices.stream()
                .map(Guest::getOfferPrice)
                .sorted(Comparator.reverseOrder())
                .limit(roomUsage)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int calculateWillingToPayForPremium(final List<Guest> willingToPayPrices) {
        return (int) willingToPayPrices.stream()
                .map(Guest::getOfferPrice)
                .filter(price -> price.compareTo(PREMIUM_LVL) >= 0)
                .count();
    }

    private int calculateWillingToPayForEconomy(final List<Guest> willingToPayPrices) {
        return (int) willingToPayPrices.stream()
                .map(Guest::getOfferPrice)
                .filter(price -> price.compareTo(PREMIUM_LVL) < 0)
                .count();
    }

}