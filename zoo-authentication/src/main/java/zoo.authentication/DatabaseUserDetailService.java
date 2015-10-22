package zoo.authentication;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author w.rittscher
 * @since 22.10.2015
 */
@Service
public class DatabaseUserDetailService implements UserDetailsService {

	private List<User> users = Collections.singletonList(
			new User("waldemar", "waldemar", AuthorityUtils.createAuthorityList("ADMIN"))
	);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return users.stream().filter(user -> user.getUsername().equals(username)).findFirst()
				.orElseThrow(() -> new UsernameNotFoundException("Failed to find user with username ["+ username + "]"));
	}
}
