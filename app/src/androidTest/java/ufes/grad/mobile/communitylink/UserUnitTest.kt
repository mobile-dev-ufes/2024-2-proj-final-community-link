package ufes.grad.mobile.communitylink

import org.junit.Test
import org.junit.Assert.assertThrows
import ufes.grad.mobile.communitylink.model.User

class UserUnitTest {
    @Test
    fun userRequiresNoEmptyFields() {
        assertThrows(IllegalArgumentException::class.java) {
            User("", "", "", "", "", "")
        }
    }
}