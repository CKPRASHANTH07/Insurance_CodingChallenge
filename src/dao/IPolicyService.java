package dao;

import entity.Policy;
import java.util.Collection;

public interface IPolicyService {
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId) throws exception.PolicyNotFoundException;
    Collection<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy) throws exception.PolicyNotFoundException;
    boolean deletePolicy(int policyId) throws exception.PolicyNotFoundException;
}