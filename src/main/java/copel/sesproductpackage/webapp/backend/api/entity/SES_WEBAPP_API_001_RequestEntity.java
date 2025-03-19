package copel.sesproductpackage.webapp.backend.api.entity;

/**
 * 要員情報検索画面のリクエスト情報を持つ.
 *
 * @author 鈴木一矢
 *
 */
public class SES_WEBAPP_API_001_RequestEntity {
	/**
	 * 検索窓に入力された対象文字列.
	 */
	private String inputText;

	/**
	 * コンストラクタ.
	 *
	 * @param inputText 入力文字列
	 */
	public SES_WEBAPP_API_001_RequestEntity(String inputText) {
		this.inputText = inputText;
	}

	/**
	 * GETTER.
	 *
	 * @return inputText
	 */
	public String getInputText() {
		return inputText;
	}
	/**
	 * SETTER.
	 *
	 * @param inputText 入力文字列
	 */
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
}
