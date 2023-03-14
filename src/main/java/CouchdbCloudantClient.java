import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.Search;
import com.cloudant.client.api.model.SearchResult;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.api.query.Selector;
import com.cloudant.client.api.query.Sort;
import com.cloudant.client.api.views.AllDocsRequest;
import com.cloudant.client.api.views.AllDocsRequestBuilder;
import com.cloudant.client.api.views.AllDocsResponse;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Expression.gt;
import static com.cloudant.client.api.query.Operation.and;


public class CouchdbCloudantClient {
    public static void main(String[] args) throws IOException {
        try{
// Note: for Cloudant Local or Apache CouchDB use:
            CloudantClient client = ClientBuilder.url(new URL("http://localhost:5984/"))
              .username("admin")
              .password("admin")
              .build();
// Show the server version
            System.out.println("Server Version: " + client.serverVersion());

            // Get a List of all the databases this Cloudant account
            List<String> databases = client.getAllDbs();
            System.out.println(client.getAllDbs());
            System.out.println(databases);

//            System.out.println("All my databases : ");
//            for ( String db : databases ) {
//                System.out.println(db);
//            }

            Database db = client.database("test_participation_evals", false);

            InputStream inputStream = db.find("100_points");
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            System.out.println(result);

//            AllDocsRequestBuilder allDocsRequestBuilder = db.getAllDocsRequestBuilder();
//            AllDocsRequest allDocsRequest = allDocsRequestBuilder.build();
//            AllDocsResponse allDocsResponse = allDocsRequest.getResponse();
//            List<String> stringList = allDocsResponse.getDocIds();

            List<String> allDocIds = db.getAllDocsRequestBuilder().build().getResponse().getDocIds();
            System.out.println(allDocIds);

            List<JsonObject> allDocs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(JsonObject.class);
            System.out.println(allDocs);

            String[] docIds = new String[]{"100_points", "10_points_per"};
            List<JsonObject> docsWithId = db.getAllDocsRequestBuilder().keys(docIds).includeDocs(true).build().getResponse().getDocsAs(JsonObject.class);
            System.out.println(docsWithId);

//              Get all documents of type "team_eval_config".
            QueryBuilder queryBuilder = new QueryBuilder(
                    eq("type", "team_eval_config"));

           QueryResult<JsonObject> queryResult =  db.query(queryBuilder.build(), JsonObject.class);
            List<JsonObject> jsonObjectList = queryResult.getDocs();
            System.out.println(jsonObjectList);


            List<JsonObject> list = db.findByIndex(" \"selector\": { \"type\": \"team_eval_config\" }", JsonObject.class);
            System.out.println("Type team_eval_config  :" + list);

//            Get all documents of type "data".

            List<JsonObject> listByData = db.findByIndex(" \"selector\": { \"type\": \"data\" }", JsonObject.class);
            System.out.println("Type Data :" + listByData);



//            Get all documents that are NOT of type "team_eval_config".
            List<JsonObject> listWithoutTec = db.findByIndex(" \"selector\": {\"type\":{\"$ne\" : \"team_eval_config\" }}", JsonObject.class);
            System.out.println("Not of TEC :" + listWithoutTec);



        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }


}

