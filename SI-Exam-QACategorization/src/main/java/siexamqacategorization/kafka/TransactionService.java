package siexamqacategorization.kafka;

import io.smallrye.mutiny.Uni;
import org.apache.kafka.common.requests.TransactionResult;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "transaction-service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TransactionService {

    @Path("/transactions")
    @POST
    TransactionResult postSync(TransactionResult transaction);

    @Path("/transactions")
    @POST
    Uni<TransactionResult> postAsync(TransactionResult transaction);

}
