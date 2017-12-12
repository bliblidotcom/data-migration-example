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
public class Migration_20171212200657 implements Migration {

  private static Logger LOG = LoggerFactory.getLogger(Migration_20171212200657.class);

  @Override
  public Long version() {
    return 20171212200657L; // DON'T EDIT THIS VERSION!!!
  }

  @Override
  public String name() {
    return "Add column email to customers table";
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
    postgreService.getContext().alterTable("customers")
        .addColumn("email", VARCHAR.length(100).nullable(false))
        .execute();

    postgreService.getContext().alterTable("customers")
        .add(constraint("customer_email").unique("email"))
        .execute();
  }

  @Override
  public void rollback() throws Exception {
    // Rollback script
    postgreService.getContext().alterTable("customers")
        .dropConstraint("customer_email");

    postgreService.getContext().alterTable("customers")
        .dropColumn("email");
  }
}
