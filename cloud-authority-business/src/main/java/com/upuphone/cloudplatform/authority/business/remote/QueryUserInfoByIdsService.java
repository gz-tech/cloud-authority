package com.upuphone.cloudplatform.authority.business.remote;
import com.upuphone.cloudplatform.common.component.BaseRemoteService;
import com.upuphone.cloudplatform.common.exception.RemoteException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import com.upuphone.cloudplatform.internal.admin.api.InternalUserClientApi;
import com.upuphone.cloudplatform.internal.admin.api.dto.request.QueryUserInfoReq;
import com.upuphone.cloudplatform.internal.admin.api.dto.response.InternalUserInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * internal-admin通过id list批量查用户信息
 */
@Service
public class QueryUserInfoByIdsService extends BaseRemoteService<QueryUserInfoReq, List<InternalUserInfoRes>, CommonResponse<List<InternalUserInfoRes>>> {
    @Autowired
    private InternalUserClientApi internalUserClientApi;

    public QueryUserInfoByIdsService(@Value("cloud-internal-admin")String service, @Value("queryByIdList")String apiName, InternalUserClientApi internalUserClientApi) {
        super(service, apiName);
        this.internalUserClientApi = internalUserClientApi;
    }

    @Override
    protected List<InternalUserInfoRes> fromRemoteResponse(CommonResponse<List<InternalUserInfoRes>> response) {
        if (response == null
                || response.getCode() != 0 || response.getData() == null) {
            throw new RemoteException(CommonErrorCode.REMOTE_ERROR);
        }
        return response.getData();
    }

    @Override
    protected CommonResponse<List<InternalUserInfoRes>> processCore(QueryUserInfoReq queryUserInfoReq) throws Exception {
        return internalUserClientApi.queryByIdList(queryUserInfoReq);
    }

    @Override
    protected String getServiceName() {
        return "cloud-internal-admin";
    }

    @Override
    protected String getApiName() {
        return "queryByIdList";
    }
}
