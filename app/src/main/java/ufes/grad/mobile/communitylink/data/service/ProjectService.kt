package ufes.grad.mobile.communitylink.data.service

import ufes.grad.mobile.communitylink.data.dao.MemberDAO
import ufes.grad.mobile.communitylink.data.dao.ProjectDAO
import ufes.grad.mobile.communitylink.data.dao.ProjectDataDAO
import ufes.grad.mobile.communitylink.data.dao.UserDAO
import ufes.grad.mobile.communitylink.data.model.ProjectModel
import ufes.grad.mobile.communitylink.data.model.ProjectStatusEnum

object ProjectService {

    suspend fun create(project: ProjectModel): Boolean {
//        if (project.currentData.id.isEmpty() && !ProjectDataDAO.insert(project.currentData))
//            return false
//
//        for (member in project.members) {
//            if (member.id.isEmpty()) {
//                member.user.memberTo.add(member)
//                if (!MemberDAO.insert(member) || !UserDAO.update(member.user)) return false
//            }
//        }
//
//        project.status = ProjectStatusEnum.PENDING
//        return ProjectDAO.insert(project)

        return true
    }

    suspend fun update(project: ProjectModel): Boolean {
//        if (
//            project.pendingData?.let {
//                return if (it.id.isEmpty()) {
//                    project.status = ProjectStatusEnum.PENDING
//                    !ProjectDataDAO.insert(it)
//                } else {
//                    false
//                }
//            } ?: false
//        )
//            return false
//
//        for (member in project.members) {
//            if (member.id.isEmpty()) {
//                member.user.memberTo.add(member)
//                if (!MemberDAO.insert(member) || !UserDAO.update(member.user)) return false
//            }
//        }
//
//        return ProjectDAO.update(project)

        return true
    }
}
