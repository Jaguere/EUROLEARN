package com.eurolearn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eurolearn.dto.ConfirmacaoPresencaDTO;
import com.eurolearn.dto.TreinamentoAgendadoDTO;
import com.eurolearn.dto.UsuarioDTO;
import com.eurolearn.models.UsuarioModel;
import com.eurolearn.repository.ConfirmacaoPresencaRepository;
import com.eurolearn.repository.TreinamentoAgendadoRepository;
import com.eurolearn.repository.UsuarioRepository;
import com.eurolearn.util.RankingMember;
import com.eurolearn.util.RankingMemberComparator;

@Service
public class RankingService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private TreinamentoAgendadoRepository treinamentoRepository;

	@Autowired
	private ConfirmacaoPresencaRepository confirmacaoRepository;

	@Autowired
	private UsuarioService userService;

	@Autowired
	private TreinamentoAgendadoService treinamentoService;

	@Autowired
	private ConfirmacaoPresencaService confirmacaoService;

	public List<RankingMember> buildRanking() {
		List<UsuarioDTO> usuarios = userService.findAll();
		List<TreinamentoAgendadoDTO> treinamentos = treinamentoService.findAll();

		List<RankingMember> ranking = new ArrayList<RankingMember>();

		for (UsuarioDTO userDTO : usuarios) {
			double qtdConfirmacoes = 0;
			double qtdInscricoes = 0;
			double taxaPresenca = 0;

			UsuarioModel userEntity = userService.findByIdModel(userDTO.getCpf());

			qtdConfirmacoes = userEntity.getConfirmacoes().size();

			for (TreinamentoAgendadoDTO treinamentoDTO : treinamentos) {
				if (treinamentoDTO.getUsuarios().contains(userEntity)) {
					qtdInscricoes++;
				}
			}
			
			System.out.println(qtdConfirmacoes);
			System.out.println(qtdInscricoes);

			if (qtdInscricoes == 0) {
				taxaPresenca = 0;
			} else {
				taxaPresenca = qtdConfirmacoes / qtdInscricoes;
				taxaPresenca *=100;
			}

			RankingMember rm = new RankingMember(userEntity.getNome(), userEntity.getSobrenome(), taxaPresenca);

			ranking.add(rm);
		}

		ranking.sort(new RankingMemberComparator());
		return ranking;
	}

}
