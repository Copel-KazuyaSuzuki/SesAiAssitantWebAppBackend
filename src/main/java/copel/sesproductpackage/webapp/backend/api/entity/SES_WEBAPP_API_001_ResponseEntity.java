package copel.sesproductpackage.webapp.backend.api.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 要員情報検索画面の情報を持つクラス.
 *
 * @author 鈴木一矢
 *
 */
public class SES_WEBAPP_API_001_ResponseEntity implements ResponseEntity {
	/**
	 * 詳細部分レコード.
	 */
	private List<SES_WEBAPP_API_001_ResponseDetailEntity> detailList;

	/**
	 * コンストラクタ.
	 */
	public SES_WEBAPP_API_001_ResponseEntity() {
		this.detailList = new ArrayList<SES_WEBAPP_API_001_ResponseDetailEntity>();
	}

	/**
	 * personInfoListにレコードを追加する.
	 *
	 * @param group 送信元グループ
	 * @param from 送信者ID
	 * @param sender 送信者名
	 * @param content 内容
	 * @param filename ファイル名
	 * @param fileurl ファイルURL
	 * @param distance 類似度
	 */
	public void add(String group, String from, String sender, String content, String filename, String fileurl, String distance) {
		this.detailList.add(new SES_WEBAPP_API_001_ResponseDetailEntity(group, from, sender, content, filename, fileurl, distance));
	}

	@Override
	public String toJson() {
		String json = "{";
		int i = 1;
		for (final SES_WEBAPP_API_001_ResponseDetailEntity entity : this.detailList) {
			json += "[" + entity.toString() + "]";
			if (i < this.detailList.size()) {
				json += ",";
			}
			i++;
		}
		json += "}";
		return json;
	}
}
