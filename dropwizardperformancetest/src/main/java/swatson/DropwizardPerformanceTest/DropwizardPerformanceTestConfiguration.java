package swatson.DropwizardPerformanceTest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DropwizardPerformanceTestConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database;

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
