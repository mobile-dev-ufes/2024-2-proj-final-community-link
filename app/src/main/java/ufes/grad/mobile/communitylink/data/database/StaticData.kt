package ufes.grad.mobile.communitylink.data.database

import java.util.Date
import ufes.grad.mobile.communitylink.data.model.ActionEventModel
import ufes.grad.mobile.communitylink.data.model.ActionModel
import ufes.grad.mobile.communitylink.data.model.ProjectDataModel
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.ProjectStatusEnum
import ufes.grad.mobile.communitylink.data.model.UserModel
import java.text.SimpleDateFormat
import java.util.Locale

object StaticData {
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val projectDataList = mutableListOf<ProjectDataModel>()
    val projects = mutableListOf<ProjectModel>()
    val users = mutableListOf<UserModel>()
    val eventActions = mutableListOf<ActionModel>()

    init {
        for (i in 0..4) {
            projectDataList.add(
                ProjectDataModel(
                    "0$i",
                    "Projeto $i",
                    "Descrição do Projeto",
                    listOf(),
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
                    listOf(),
                    listOf(),
                    listOf()
                )
            )
        }

        for (i in 0..4) {
            users.add(
                UserModel(
                    "0$i",
                    "Usuário $i",
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            )
        }

        for (i in 0..4) {
            eventActions.add(
                ActionEventModel(
                    "0$i",
                    "Evento $i",
                    "Descrição do Evento $i",
                    listOf(),
                    dateFormatter.format(Date()),
                    dateFormatter.format(Date()),
                    true,
                    emptyList(),
                    projects[i],
                    users[i],
                )
            )
        }
    }
}
