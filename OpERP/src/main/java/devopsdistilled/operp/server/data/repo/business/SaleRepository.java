package devopsdistilled.operp.server.data.repo.business;

import org.springframework.data.jpa.repository.JpaRepository;

import devopsdistilled.operp.server.data.entity.business.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
