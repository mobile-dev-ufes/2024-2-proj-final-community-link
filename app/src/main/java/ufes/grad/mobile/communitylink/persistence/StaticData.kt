package ufes.grad.mobile.communitylink.persistence

import ufes.grad.mobile.communitylink.model.ActionEventModel
import ufes.grad.mobile.communitylink.model.ActionModel
import ufes.grad.mobile.communitylink.model.ProjectDataModel
import ufes.grad.mobile.communitylink.model.ProjectModel
import ufes.grad.mobile.communitylink.model.ProjectStatusEnum
import ufes.grad.mobile.communitylink.model.UserModel
import java.util.Date

object StaticData {
    val projectDataList = mutableListOf<ProjectDataModel>()
    val projects = mutableListOf<ProjectModel>()
    val users = mutableListOf<UserModel>()
    val eventActions = mutableListOf<ActionModel>()

    init {
        for (i in 0..4) {
            projectDataList.add(ProjectDataModel(
                "0$i",
                "Projeto $i",
                "Descrição do Projeto",
                listOf(),
                "",
                "",
                "",
                Date(),
                false,
                ""
            ))
        }

        for (i in 0..4) {
            projects.add(ProjectModel(
                "0$i",
                ProjectStatusEnum.ACCEPTED,
                projectDataList[i],
                null,
                listOf(),
                listOf(),
                listOf()
            ))
        }

        for (i in 0..4) {
            users.add(UserModel(
                "0$i",
                "Usuário $i",
                "",
                "",
                Date(),
                "",
                "",
                listOf(),
                listOf(),
                listOf(),
                listOf(),
                listOf()
            ))
        }

        for (i in 0..4) {
            eventActions.add(ActionEventModel(
                "0$i",
                "Evento $i",
                "Descrição do Evento $i",
                listOf(),
                Date(),
                Date(),
                true,
                listOf(),
                projects[i],
                users[i],
                null,
                listOf(),
                listOf()
            ))
        }
    }
}
