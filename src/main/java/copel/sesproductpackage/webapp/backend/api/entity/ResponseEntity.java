package copel.sesproductpackage.webapp.backend.api.entity;

/**
 * レスポンスデータ
 *
 * @author 鈴木一矢
 *
 */
public interface ResponseEntity {
	/**
	 * このオブジェクトからレスポンスボディに設定するためのJSON文字列を生成する.
	 *
	 * @return JSON文字列
	 */
	public String toJson();
}
