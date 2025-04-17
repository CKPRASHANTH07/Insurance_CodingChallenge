package dao;

import entity.Policy;
import util.DBConnUtil;
import exception.PolicyNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class InsuranceServiceImpl implements IPolicyService {
    private Connection connection;

    public InsuranceServiceImpl() {
        this.connection = DBConnUtil.getConnection();
    }

    @Override
    public boolean createPolicy(Policy policy) {
        String sql = "INSERT INTO Policy (policyId, policyNumber, policyType, coverageAmount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, policy.getPolicyId());
            stmt.setString(2, policy.getPolicyNumber());
            stmt.setString(3, policy.getPolicyType());
            stmt.setDouble(4, policy.getCoverageAmount());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) throws PolicyNotFoundException {
        String sql = "SELECT * FROM Policy WHERE policyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, policyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyNumber"),
                    rs.getString("policyType"),
                    rs.getDouble("coverageAmount")
                );
            } else {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PolicyNotFoundException("Error retrieving policy with ID " + policyId);
        }
    }

    @Override
    public Collection<Policy> getAllPolicies() {
        Collection<Policy> policies = new ArrayList<>();
        String sql = "SELECT * FROM Policy";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                policies.add(new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyNumber"),
                    rs.getString("policyType"),
                    rs.getDouble("coverageAmount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) throws PolicyNotFoundException {
        String sql = "UPDATE Policy SET policyNumber = ?, policyType = ?, coverageAmount = ? WHERE policyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, policy.getPolicyNumber());
            stmt.setString(2, policy.getPolicyType());
            stmt.setDouble(3, policy.getCoverageAmount());
            stmt.setInt(4, policy.getPolicyId());
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new PolicyNotFoundException("Policy with ID " + policy.getPolicyId() + " not found.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) throws PolicyNotFoundException {
        String sql = "DELETE FROM Policy WHERE policyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, policyId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}