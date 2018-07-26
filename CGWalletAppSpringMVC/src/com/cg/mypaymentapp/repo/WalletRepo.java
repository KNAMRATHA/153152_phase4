
//WalletRepo Interface

package com.cg.mypaymentapp.repo;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;


public interface WalletRepo extends JpaRepository<Customer, String>{
	@Query("select c from Customer c join c.wallet w where w.balance<:bal")
	List<Customer> find(@Param("bal")BigDecimal bal);
}
