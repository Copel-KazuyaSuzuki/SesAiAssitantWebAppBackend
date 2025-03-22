package copel.sesproductpackage.webapp.backend;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import copel.sesproductpackage.webapp.backend.api.ProcessType;
import copel.sesproductpackage.webapp.backend.api.SES_WEBAPP_API_001;
import copel.sesproductpackage.webapp.backend.api.entity.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * 【SES AIアシスタント】
 * Lambdaがリクエストを受け付け、処理を開始するMainクラス.
 *
 * @author 鈴木一矢
 *
 */
@Slf4j
public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        log.info("[Invoke ID: {}] {}", context.getAwsRequestId(), input.toString());

        // (0) レスポンスを準備
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setHeaders(Map.of(
                "Content-Type", "application/json",
                "Access-Control-Allow-Origin", input.getHeaders().get("origin"),
                "Access-Control-Allow-Methods", "OPTIONS, POST, GET",
                "Access-Control-Allow-Headers", "Content-Type, Authorization",
                "Access-Control-Allow-Credentials", "true"
            ));

        // (0) OPTIONSリクエストなら即レスポンス
        log.info(input.getHttpMethod());
        if ("OPTIONS".equalsIgnoreCase(input.getHttpMethod())) {
            response.setStatusCode(200);
            response.setBody("{}");
            log.info("[Invoke ID: {}] OPTIONSリクエストを受信しました。CORSヘッダーを返します。{}", context.getAwsRequestId(), response);
            return response;
        }

        // (1) 空のリクエストの場合、処理終了
    	if (input.getBody() == null) {
            response.setStatusCode(400);
            response.setBody("{\"message\": \"リクエストボディが空です。\"}");
    		log.error("[Invoke ID: {}] リクエストボディが空のリクエストのため、処理を行わず終了します。{}", context.getAwsRequestId(), response);
            return response;
    	}

        // (2) リクエストボディから入力を取り出す
		log.info("[Invoke ID: {}] リクエストBody:{}", context.getAwsRequestId(), input.getBody());

		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(input.getBody());
		} catch (JsonProcessingException e) {
            response.setStatusCode(400);
            response.setBody("{\"message\": \"リクエストボディの形式が異常です。\"}");
    		log.error("[Invoke ID: {}] リクエストボディの形式が異常のため、処理を行わず終了します。{}", context.getAwsRequestId(), response);
            return response;
		}

		// (2-1) "process_type"が存在しない場合、400エラー
        if (!rootNode.has("process_type")) {
            response.setStatusCode(400);
            response.setBody("{\"message\": \"process_typeが存在しません。\"}");
            log.error("[Invoke ID: {}] process_typeが存在しないため、処理を終了します。{}", context.getAwsRequestId(), response);
            return response;
        }

        // (3) 処理種別を取得
        ProcessType processType = ProcessType.getEnumByCode(rootNode.path("process_type").asText());
        log.info("[Invoke ID: {}] 「{}」を開始します。", context.getAwsRequestId(), processType.name());

        // (4) 処理を実行
        ResponseEntity responseEntity = null;
        switch (processType) {
	        case 要員情報検索画面_全文検索:
	        	SES_WEBAPP_API_001 api = new SES_WEBAPP_API_001(rootNode.path("input_text").asText());
	        	api.execute();
	        	responseEntity = api.getResponseEntity();
	        	break;
	        case 要員情報検索画面_ベクトル検索:
	        	break;
	        case 要員情報検索画面_AI検索:
	        	break;
	        case 案件情報検索画面_全文検索:
	        	break;
	        case 案件情報検索画面_ベクトル検索:
	        	break;
	        case 案件情報検索画面_AI検索:
	        	break;
	        case スキルシート情報検索画面_全文検索:
	        	break;
	        case スキルシート情報検索画面_ベクトル検索:
	        	break;
	        case スキルシート情報検索画面_AI検索:
	        	break;
	        default:
	            response.setStatusCode(400);
	            response.setBody("{\"message\": \"対象外のprocess_typeが送信されました。\"}");
	            log.error("[Invoke ID: {}] process_typeが異常のため、処理を終了します。{}", context.getAwsRequestId(), response);
	            return response;
        }

        // (5) レスポンスを作成し返却する
        response.setStatusCode(200);
        response.setBody(responseEntity.toJson());
        log.info("[Invoke ID: {}] 正常に処理を終了します。{}", context.getAwsRequestId(), response);
        return response;
    }
}
