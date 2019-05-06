package com.wolfheart.serviceImpl;

import com.wolfheart.beans.Conversation;
import com.wolfheart.beans.User;
import com.wolfheart.dao.IUserDao;
import com.wolfheart.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author  WolfHeart
 *  用户业务服务实体类
 */

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao dao;

    public void setDao(IUserDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) {

        dao.insertUser(user);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User selectUserByNamePassword(User user) {

        return dao.selectUser(user);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserState(User user, int loginState) {

        dao.updateUserState(user, loginState);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User checkUsername(String username) {
        return dao.selectUserByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveConversation(Conversation conversation) {
        dao.insertConversation(conversation);
    }

    @Override
    public List<Conversation> listConversationByLoginTime(Date loginTime) {
        return dao.selectConversationByTime(loginTime);
    }

    @Override
    public int countUsersOnline() {
        return dao.countUsersOnline();
    }

    @Override
    public List<User> listUsersOnline() {
        return dao.listUsersOnline();
    }

}
