package migrations;

import com.gdn.data.migration.core.*;
import com.gdn.data.migration.postgre.PostgreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.jooq.impl.DSL.*;
import static org.jooq.util.postgres.PostgresDataType.*;

/**
 * Auto generated migration script
 */
@Component
public class Migration_20171212200649 implements Migration {

  private static Logger LOG = LoggerFactory.getLogger(Migration_20171212200649.class);

  @Override
  public Long version() {
    return 20171212200649L; // DON'T EDIT THIS VERSION!!!
  }

  @Override
  public String name() {
    return "Create table customers";
  }

  // @Autowired
  // private MongoConfiguration mongoConfiguration;

  // @Autowired
  // private PostgreConfiguration postgreConfiguration;

  // @Autowired
  // private ElasticsearchConfiguration elasticsearchConfiguration;

  // @Autowired
  // private OkHttpClient okHttpClient;

  // @Autowired
  // private ObjectMapper objectMapper;

  @Autowired
  private PostgreService postgreService;

  // @Autowired
  // private MongoDatabase mongoDatabase;

  @Override
  public void migrate() throws Exception {
    // Migration script
    postgreService.getContext().createTable("customers")
        .column("id", VARCHAR.length(100))
        .column("name", VARCHAR.length(50).nullable(false))
        .column("address", VARCHAR.length(500).nullable(true))
        .constraint(constraint("customer_id").primaryKey("id"))
        .execute();
  }

  @Override
  public void rollback() throws Exception {
    // Rollback script
    postgreService.getContext().dropTable("customers");
  }
}
