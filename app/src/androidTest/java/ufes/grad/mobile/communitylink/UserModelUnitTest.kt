package ufes.grad.mobile.communitylink

import org.junit.Test
import org.junit.Assert.assertThrows
import ufes.grad.mobile.communitylink.model.UserModel

class UserModelUnitTest {
    @Test
    fun userRequiresNoEmptyFields() {
        assertThrows(IllegalArgumentException::class.java) {
            UserModel("", "", "", "", "", "")
        }
    }
}