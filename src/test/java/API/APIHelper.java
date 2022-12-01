package API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

public class APIHelper extends BaseAPI{
    public APIHelper(){

    }
    public Response getSimpleResponse(ContentType aContentType, String aResource){
        return given().log().all().contentType(aContentType)
                .when().log().all()
                .get(aResource);

    }
    public Response getSimpleResponse(ContentType aContentType){
        return given().log().all().contentType(aContentType)
                .when().log().all()
                .get();

    }
    public Response postSimpleResponse(Object aBody, String aResource, ContentType aContentType){
        return given().log().all()
                .contentType(aContentType)
                .and().body(aBody)
                .when().log().all()
                .post(aResource);

    }
    public Response postSimpleResponseWithJsonObject(JSONObject aBody, String aResource, ContentType aContentType){
        return given().log().all()
                .contentType(aContentType)
                .and().body(aBody)
                .when().log().all()
                .post(aResource);

    }
    public Response putSimpleResponse(String aBody, String aResource, ContentType aContentType){
        return given().log().all().contentType(aContentType)
                .and().body(aBody)
                .when().log().all()
                .put(aResource);

    }
    public Response patchSimpleResponse(Object aBody, String aResource, ContentType aContentType){
        return given().log().all().contentType(aContentType)
                .and().body(aBody)
                .when().log().all()
                .patch(aResource);

    }
    public Response deleteSimpleResponse(String aResource, ContentType aContentType){
        return given().log().all().contentType(aContentType)
                .when().log().all()
                .delete(aResource);

    }
    public Response getSimpleResponseSendingParams(String aResource, HashMap<String, String> aHashMap, String aContentType){
        return given().log().all().header("Content-Type", aContentType)
                .queryParams(aHashMap)
                .when().log().all()
                .get(aResource);

    }
    public Response postSimpleResponseSendingParams(String aResource, HashMap<String, String> aHashMap, String aContentType){
        return given().log().all().header("Content-Type", aContentType)
                .queryParams(aHashMap)
                .when().log().all()
                .post(aResource);

    }
    public int getStatusCode(Response aResponse){
        return aResponse.getStatusCode();

    }
    public String prettyPrintResponse(Response aResponse){
        String responseBody = "";
        try{
            responseBody = aResponse.prettyPrint();
        }catch(NullPointerException npe){
            responseBody= "The response is null";
        }

        return responseBody;

    }
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public String getPerfectParsedResponse(String unformattedJSON) {
        String perfectJSON;
        try {
            perfectJSON = GSON.toJson(JsonParser.parseString(unformattedJSON));

        }catch(Exception e) {
            Document doc = convertStringToDocument(unformattedJSON);
            perfectJSON = convertDocumentToString(doc);

        }
        return perfectJSON;

    }
    private static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            return writer.getBuffer().toString();

        } catch (TransformerException e) {
            e.printStackTrace();

        }

        return null;
    }
    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse( new InputSource( new StringReader( xmlStr ) ) );

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }
    public JSONObject jsonObjectFromJsonArray(JSONArray jArr, int pos){
        return new JSONObject(jArr.get(pos).toString());

    }
    public String constructorToJson(Object anObject){
        Gson gson = new Gson();
        return gson.toJson(anObject);

    }

}
