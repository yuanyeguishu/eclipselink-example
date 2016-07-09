package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental.fluent_criteria;

public class FluentCriteria {

    public SelectClause select() {
        return new SelectClause();
    }

    public static void main(String[] args) {
        FluentCriteria fluentCriteria = new FluentCriteria();
        fluentCriteria
                .select()
                .where();
    }
}
