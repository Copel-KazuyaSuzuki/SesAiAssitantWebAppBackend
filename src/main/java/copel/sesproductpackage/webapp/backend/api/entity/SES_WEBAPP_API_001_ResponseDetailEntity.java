package copel.sesproductpackage.webapp.backend.api.entity;

/**
 * 要員情報検索画面の詳細部分の1レコード分の情報を持つクラス.
 *
 * @author 鈴木一矢
 *
 */
public class SES_WEBAPP_API_001_ResponseDetailEntity {
    /**
     * 送信元グループ.
     */
    private String group;
    /**
     * 送信者ID.
     */
    private String from;
    /**
     * 送信者名.
     */
    private String sender;
    /**
     * 内容.
     */
    private String content;
    /**
     * ファイル名.
     */
    private String filename;
    /**
     * ファイルURL.
     */
    private String fileurl;
    /**
     * 類似度.
     */
    private String distance;

    /**
     * コンストラクタ.
     *
     * @param group 送信元グループ
     * @param from 送信者ID
     * @param sender 送信者名
     * @param content 内容
     * @param filename ファイル名
     * @param fileurl ファイルURL
     * @param distance 類似度
     */
    public SES_WEBAPP_API_001_ResponseDetailEntity(String group, String from, String sender, String content, String filename, String fileurl, String distance) {
        this.group = group;
        this.from = from;
        this.sender = sender;
        this.content = content;
        this.filename = filename;
        this.fileurl = fileurl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "\"group\":\"" + this.group + "\","
                + "\"from\":\"" + this.from + "\","
                + "\"sender\":\"" + this.sender + "\","
                + "\"content\":\"" + this.content + "\","
                + "\"filename\":\"" + this.filename + "\","
                + "\"fileurl\":\"" + this.fileurl + "\","
                + "\"distance\":\"" + this.distance + "\"";
    }
}
