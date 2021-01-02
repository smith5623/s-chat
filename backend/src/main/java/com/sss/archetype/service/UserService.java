package com.sss.archetype.service;

import com.sss.archetype.converter.Converter;
import com.sss.archetype.dao.UserMapper;
import com.sss.archetype.dto.UserDTO;
import com.sss.archetype.entity.User;
import com.sss.archetype.util.IdWorker;
import com.sss.archetype.util.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class UserService extends BaseService<UserDTO, User, java.lang.String,UserMapper> {
    @Autowired
    private QRCodeUtils qrCodeUtils;

    @Autowired
    private Converter<UserDTO,User> converter;

    @Override
    public User doForward(UserDTO dto) {
        return converter.doForward(dto);
    }

    @Override
    public UserDTO doBackward(User entity) {
        return converter.doBackward(entity);
    }

    public boolean queryUsernameIsExist(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> result = mapper.listAll(user);
        return (result != null && result.size()==1) ? true : false;
    }

    public User queryUserForLogin(String username, String md5Str) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5Str);
        List<User> result = mapper.listAll(user);
        if(result != null && result.size() ==1){
            return result.get(0);
        }else{
            return null;
        }
    }

    public User saveUser(User user) {
        String userId = IdWorker.getId() + "";
        // 为每个用户生成一个唯一的二维码
        /*String qrCodePath = "d://user" + IdWorker.getId() + "qrcode.png";
        // muxin_qrcode:[username]

        qrCodeUtils.createQRCode(qrCodePath, "muxin_qrcode:" + user.getUsername());
        MultipartFile qrCodeFile = FileUtils.fileToMultipart(qrCodePath);

        String qrCodeUrl = "";
        try {
            qrCodeUrl = fastDFSClient.uploadQRCode(qrCodeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setQrcode(qrCodeUrl);*/

        user.setId(userId);
        mapper.insert(user);
        return user;
    }
}
