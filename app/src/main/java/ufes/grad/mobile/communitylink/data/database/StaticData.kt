package ufes.grad.mobile.communitylink.data.database

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import ufes.grad.mobile.communitylink.data.model.ActionDonationModel
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.DonationForActionModel
import ufes.grad.mobile.communitylink.data.model.DonationForProjectModel
import ufes.grad.mobile.communitylink.data.model.GoalModel
import ufes.grad.mobile.communitylink.data.model.GoalPostModel
import ufes.grad.mobile.communitylink.data.model.GoalPostTypeEnum
import ufes.grad.mobile.communitylink.data.model.PostModel
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.ProjectStatusEnum
import ufes.grad.mobile.communitylink.data.model.SlotRequestModel
import ufes.grad.mobile.communitylink.data.model.UserModel
import ufes.grad.mobile.communitylink.data.model.VolunteerSlotModel

object StaticData {
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val projectDataList = mutableListOf<ProjectDataModel>()
    val projects = mutableListOf<ProjectModel>()
    val users = mutableListOf<UserModel>()
    val eventActions = mutableListOf<ActionEventModel>()
    val donationActions = mutableListOf<ActionDonationModel>()
    val donationsToAction = mutableListOf<DonationForActionModel>()
    val donationsToProject = mutableListOf<DonationForProjectModel>()
    val posts = mutableListOf<PostModel>()
    val goalPosts = mutableListOf<GoalPostModel>()
    val goals = mutableListOf<GoalModel>()
    val slotRequests = mutableListOf<SlotRequestModel>()
    val slots = mutableListOf<VolunteerSlotModel>()

    init {
        for (i in 0..4) {
            projectDataList.add(
                ProjectDataModel(
                    "0$i",
                    "Projeto $i",
                    "Descrição do Projeto",
                    mutableListOf(),
                    "",
                    "",
                    "",
                    dateFormatter.format(Date()),
                    false,
                    ""
                )
            )
        }

        for (i in 0..4) {
            projects.add(
                ProjectModel(
                    "0$i",
                    ProjectStatusEnum.ACCEPTED,
                    projectDataList[i],
                    null,
                )
            )
        }

        for (i in 0..4) {
            users.add(UserModel("0$i", "Usuário $i", "", "", "", "", ""))
        }

        for (i in 0..4) {
            eventActions.add(
                ActionEventModel(
                    "V6T3kVOvOXjxTsjoCDAe",
                    "Evento $i",
                    "Descrição do Evento $i",
                    mutableListOf(),
                    dateFormatter.format(Date()),
                    dateFormatter.format(Date()),
                    true,
                    ProjectModel("wL4SFYZIEAcf0ELI15gb"),
                    users[i],
                )
            )
        }

        for (i in 0..4) {
            posts.add(
                PostModel(
                    "0$i",
                    text =
                        "Postagem $i... Esta postagem é muito boa.\nTestando os limites\nSim, testando\nUau asdaaaaaaaa jasdjajd afassjnanj asdkankdsknka askdnakdknaksdk askdkansdkna",
                    date = dateFormatter.format(Date()),
                    media = "listOf()"
                )
            )
        }

        for (i in 0..4) {
            donationActions.add(
                ActionDonationModel(
                    "5adAZTHPthmQ1HQGid5a",
                    "Doação $i",
                    "Descrição da doação $i",
                    mutableListOf(),
                    dateFormatter.format(Date()),
                    dateFormatter.format(Date()),
                    true,
                    ProjectModel("wL4SFYZIEAcf0ELI15gb"),
                    users[i],
                )
            )
        }

        for (i in 0..4) {
            goalPosts.add(
                GoalPostModel(
                    "0$i",
                    type = GoalPostTypeEnum.MODIFIED,
                    description =
                        "Esta meta $i foi feita para bla bla bla...\nMais coisa aqui...\nMais coisa ainda...\nTexto",
                    amount = 10.0f,
                    date = dateFormatter.format(Date()),
                    actionDonation = donationActions[i]
                )
            )
        }

        for (i in 0..4) {
            goals.add(
                GoalModel(
                    "0$i",
                    description =
                        "Esta meta $i foi feita para bla bla bla...\nMais coisa aqui...\nMais coisa ainda...\nTexto",
                    minimalQuantity = 0.0f,
                    actualQuantity = 0.0f
                )
            )
        }

        for (i in 0..4) {
            slots.add(
                VolunteerSlotModel(
                    "0$i",
                    position = "balela",
                    initDate = "00/00/2020",
                    finishDate = "00/00/2021",
                    place = "Minha casa"
                )
            )
        }

        for (i in 0..4) {
            slotRequests.add(SlotRequestModel("0$i", date = "12/02/2012"))
        }

        for (i in 0..4) {
            donationsToAction.add(
                DonationForActionModel("0$i", value = 12.00f, date = "12/02/2012")
            )
        }

        for (i in 0..4) {
            donationsToProject.add(
                DonationForProjectModel(
                    "0$i",
                    value = 1.0f,
                    objectName = "sofá",
                    date = "12/02/2012"
                )
            )
        }
    }
}
