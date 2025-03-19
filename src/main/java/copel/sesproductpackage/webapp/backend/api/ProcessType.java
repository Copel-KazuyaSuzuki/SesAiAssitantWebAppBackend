package copel.sesproductpackage.webapp.backend.api;

import java.util.Arrays;

/**
 * 処理種別.
 *
 * @author 鈴木一矢
 *
 */
public enum ProcessType {
	要員情報検索画面_全文検索("11"),
	要員情報検索画面_ベクトル検索("12"),
	要員情報検索画面_AI検索("13"),
	案件情報検索画面_全文検索("21"),
	案件情報検索画面_ベクトル検索("22"),
	案件情報検索画面_AI検索("23"),
	スキルシート情報検索画面_全文検索("31"),
	スキルシート情報検索画面_ベクトル検索("32"),
	スキルシート情報検索画面_AI検索("33");

	/**
	 * コード値.
	 */
	private String code;

	/**
	 * コンストラクタ.
	 *
	 * @param code コード値
	 */
	ProcessType (final String code) {
		this.code = code;
	}

    /**
     * コード値から対応するProcessTypeを取得する.
     *
     * @param code コード値
     * @return 対応するProcessType
     * @throws IllegalArgumentException 該当するコードがない場合
     */
    public static ProcessType getEnumByCode(final String code) throws IllegalArgumentException {
        return Arrays.stream(values())
                .filter(pt -> pt.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
    }

    /**
     * コード値を取得する.
     *
     * @return コード値
     */
    public String getCode() {
        return this.code;
    }
}
