package copel.sesproductpackage.webapp.backend.api;

import copel.sesproductpackage.webapp.backend.api.entity.ResponseEntity;
import copel.sesproductpackage.webapp.backend.api.entity.SES_WEBAPP_API_001_RequestEntity;
import copel.sesproductpackage.webapp.backend.api.entity.SES_WEBAPP_API_001_ResponseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * 要員情報をベクトル検索した結果を返却するAPI.
 *
 * @author 鈴木一矢
 *
 */
@Slf4j
public class SES_WEBAPP_API_001 {
    /**
     * リクエスト.
     */
    private SES_WEBAPP_API_001_RequestEntity request;
    /**
     * レスポンス.
     */
    private SES_WEBAPP_API_001_ResponseEntity response;

    /**
     * コンストラクタ.
     *
     * @param body リクエストボディ
     */
    public SES_WEBAPP_API_001(final String inputText) {
        this.response = new SES_WEBAPP_API_001_ResponseEntity();
        this.request = new SES_WEBAPP_API_001_RequestEntity(inputText);
    }

    /**
     * このAPIを実行する.
     */
    public void execute() {
        this.response.add("テスト送信元グループ", "テスト送信者ID", "テスト送信者名", "内容はこれです" + this.request.getInputText(), "テストファイル名", "テストファイルURL", "1.234");
    }

    /**
     * このAPIのレスポンスを取得する.
     *
     * @return ResponseEntity
     */
    public ResponseEntity getResponseEntity() {
    	return this.response;
    }
}
