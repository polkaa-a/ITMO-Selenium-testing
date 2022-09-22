package reader;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigReader {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
    }

    String URL = readConfig().getString("url");

    String SHOP_URL = readConfig().getString("shop_url");

    String APPLE_URL = readConfig().getString("apple_url");
    String APP_NAME = readConfig().getString("app_name");

    String CANVAS_TEXT = readConfig().getString("canvas_text");

    String PROJECT_NAME = readConfig().getString("project_name");
    String PROJECT_SIZE = readConfig().getString("project_size");
    String PROJECT_STYLE = readConfig().getString("project_style");

    String PHOTO_NAME = readConfig().getString("photo_name");
    String PHOTO_SEARCH = readConfig().getString("photo_search");

    String SORT_FIRST_PHOTO_NAME = readConfig().getString("sort_first_photo_name");
    String SORT_FIRST_PHOTO_SEARCH = readConfig().getString("sort_first_photo_search");

    String SORT_SECOND_PHOTO_NAME = readConfig().getString("sort_second_photo_name");
    String SORT_SECOND_PHOTO_SEARCH = readConfig().getString("sort_second_photo_search");
}
