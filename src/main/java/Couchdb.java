//import com.sun.net.httpserver.BasicAuthenticator;
//
//import java.io.IOException;
//
//
//public class Couchdb {
//    public static void main(String[] args) throws IOException {
//        try{
//        BasicAuthenticator authenticator = new BasicAuthenticator.Builder()
//                .username("admin")
//                .password("admin")
//                .build();
//
//        // 1. Create a client with `CLOUDANT` default service name ============
//        Cloudant client = new Cloudant(Cloudant.DEFAULT_SERVICE_URL, authenticator);
//
//        // 2. Get server information ==========================================
//        ServerInformation serverInformation = client
//                .getServerInformation()
//                .execute()
//                .getResult();
//
//        System.out.println("Server Version: " +
//                serverInformation.getVersion());
//
//        // 3. Get database information for "test_participation_evals" =========================
//        String dbName = "test_participation_evals";
//
//        GetDatabaseInformationOptions dbInformationOptions =
//                new GetDatabaseInformationOptions.Builder(dbName).build();
//
//        DatabaseInformation dbInformationResponse = client
//                .getDatabaseInformation(dbInformationOptions)
//                .execute()
//                .getResult();
//
//        // 4. Show document count in database =================================
//        Long documentCount = dbInformationResponse.getDocCount();
//
//        System.out.println("Document count in \"" +
//                dbInformationResponse.getDbName() +
//                "\" database is " + documentCount +
//                ".");
//
//        // 5. Get "example" document out of the database by document id ===========
//      GetDocumentOptions getDocOptions = new GetDocumentOptions.Builder()
//              .db(dbName)
//              .docId("10_points_per")
//              .build();
//
//        Document documentExample = client
//                .getDocument(getDocOptions)
//                .execute()
//                .getResult();
//
//        System.out.println("Document retrieved from database:\n" +
//                documentExample);
//
//        // 6. Get all documents of type "team_eval_config".   You should get 2 document back.
//
////            QueryBuilder queryBuilder = new QueryBuilder(in("type", "team_eval_config"));
////            System.out.println(queryBuilder);
//
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//}
