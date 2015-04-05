/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.model;

/**
 * Enum for the different types of payment for a lesson payment.
 */
public enum PaymentType {
    /**
     * Payment by credit or debit card.
     */
    CARD {
                @Override
                public String toString() {
                    return "Credit/Debit Card";
                }
            },
    /**
     * Payment by cheque.
     */
    CHEQUE {
                @Override
                public String toString() {
                    return "Cheque";
                }
            },
    /**
     * Payment by cash.
     */
    CASH {
                @Override
                public String toString() {
                    return "Cash";
                }
            }
}
