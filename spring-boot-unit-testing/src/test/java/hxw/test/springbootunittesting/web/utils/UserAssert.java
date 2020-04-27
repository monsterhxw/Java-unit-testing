package hxw.test.springbootunittesting.web.utils;

import hxw.test.springbootunittesting.persistence.entity.UserEntity;
import org.assertj.core.api.AbstractAssert;

public class UserAssert extends AbstractAssert<UserAssert, UserEntity> {

    public UserAssert(UserEntity userEntity) {
        super(userEntity, UserAssert.class);
    }

    public static UserAssert assertThat(UserEntity actual) {
        return new UserAssert(actual);
    }

    public UserAssert hasRegistrationDate() {
        isNotNull();
        if (actual.getRegistrationDate() == null) {
            failWithMessage("Expected user to have a registration date, but it was null");
        }
        return this;
    }
}
