//package refactor.naver.reserve.reserveweb_refactor.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//@Configuration
//public class RoutingDataSource extends AbstractRoutingDataSource {
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) ? "slave" : "master";
//    }
//}
