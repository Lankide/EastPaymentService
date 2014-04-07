//package ua.globallogic.eastpaymentservice.repository.jdbc;
//
//
//import org.intellij.lang.annotations.Language;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import ua.globallogic.eastpaymentservice.domain.Proposal;
//import ua.globallogic.eastpaymentservice.repository.ProposalRepository;
//import ua.globallogic.eastpaymentservice.repository.support.ProposalRowMapper;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@Repository
//public class JdbcProposalRepository implements ProposalRepository {
//
//    @Language("SQL")
//    private final String ADD_PROPOSAL = "INSERT INTO bank_proposal (bank_id, currency_code, commission) VALUES (?, ?, ?)";
//    @Language("SQL")
//    private final String GET_PROPOSAL_BY_ID = "SELECT * FROM bank_proposal WHERE id = ?";
//    @Language("SQL")
//    private final String GET_PROPOSALS_BY_BANK_ID = "SELECT bank_proposal.*, currency.name FROM bank_proposal, currency WHERE bank_id = ? AND currency_code = code";
//    @Language("SQL")
//    private final String GET_PROPOSALS_BY_CURRENCY_CODE = "SELECT * FROM bank_proposal WHERE currency_code = ?";
//
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JdbcProposalRepository(DataSource dataSource){
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public void addProposal(Proposal proposal){
//        jdbcTemplate.update(ADD_PROPOSAL, proposal.getParams());
//    }
//
//    @Override
//    public Proposal getProposalById(Integer id){
//        return jdbcTemplate.queryForObject(GET_PROPOSAL_BY_ID, new Object[]{id}, new ProposalRowMapper());
//    }
//
//    @Override
//    public List<Proposal> getProposalsByBankId(Integer bankId){
//        return jdbcTemplate.query(GET_PROPOSALS_BY_BANK_ID, new Object[]{bankId}, new ProposalRowMapper());
//    }
//
//    @Override
//    public List<Proposal> getProposalsByCurrencyCode(Integer currencyCode){
//        return jdbcTemplate.query(GET_PROPOSALS_BY_CURRENCY_CODE, new Object[]{currencyCode}, new ProposalRowMapper());
//    }
//}
