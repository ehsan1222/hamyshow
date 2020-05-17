package ir.hamyiar.hamyshow.dao;

import ir.hamyiar.hamyshow.model.user.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

}
