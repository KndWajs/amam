package pl.fit_amam.api.aws;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import org.springframework.stereotype.Component;

@Component
public class SSMClient {

    public static String getParameter(String key, boolean encryption)  {
        AWSSimpleSystemsManagement ssm = AWSSimpleSystemsManagementClientBuilder.defaultClient();
        GetParameterRequest getparameterRequest = new GetParameterRequest().withName(key).withWithDecryption(encryption);
        final GetParameterResult result = ssm.getParameter(getparameterRequest);
        return result.getParameter().getValue();
    }
}