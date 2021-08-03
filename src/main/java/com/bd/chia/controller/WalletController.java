package com.bd.chia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bd.chia.jpa.WinningRecord;
import com.bd.chia.repository.WinningRecordRepository;

@RestController
@RequestMapping(path = "/wallet")
public class WalletController {
	@Autowired
	WinningRecordRepository winningRecordRepository;
	
	@GetMapping(path = "/winning")
	public List<WinningRecord> getChiaWinning() {
		return winningRecordRepository.findAllByType(WinningRecord.INCOMING);
	}
}
