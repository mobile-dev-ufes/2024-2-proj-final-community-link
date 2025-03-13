package ufes.grad.mobile.communitylink

import org.junit.Assert.assertThrows
import org.junit.Test
import ufes.grad.mobile.communitylink.model.UserModel

class UserModelUnitTest {
    @Test
    fun userRequiresNoEmptyFields() {
        assertThrows(IllegalArgumentException::class.java) { UserModel("", "", "", "", "", "") }
    }
}
