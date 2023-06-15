package kr.teentime.mainApi.service;

import kr.teentime.mainApi.repository.AdminMemberRepository;
import kr.teentime.mainApi.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMemberRepository adminMemberRepository;
}
