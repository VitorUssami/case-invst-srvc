using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Globalization;


//Obs: Voce é livre para implementar na linguagem de sua preferência, desde que respeite as funcionalidades e saídas existentes, além de aplicar os conceitos solicitados.
//to run: dotnet run
namespace app
{
    class Program
    {

        static void Main(string[] args)
        {
            var TRANSACOES = new transacao[] { 
                                    new transacao(1,DateTime.ParseExact("09/09/2023 14:15:00", "dd/MM/yyyy HH:mm:ss", CultureInfo.InvariantCulture), 938485762, 2147483649, 150),    //ok
                                    new transacao(2,DateTime.ParseExact("09/09/2023 14:15:05", "dd/MM/yyyy HH:mm:ss", CultureInfo.InvariantCulture), 2147483649, 210385733, 149),    //ok
                                    new transacao(3,DateTime.ParseExact("09/09/2023 14:15:29", "dd/MM/yyyy HH:mm:ss", CultureInfo.InvariantCulture), 347586970, 238596054, 1100),    //ok
                                    new transacao(4,DateTime.ParseExact("09/09/2023 14:17:00", "dd/MM/yyyy HH:mm:ss", CultureInfo.InvariantCulture), 675869708, 210385733, 5300),    //NOK
                                    new transacao(5,DateTime.ParseExact("09/09/2023 14:18:00", "dd/MM/yyyy HH:mm:ss", CultureInfo.InvariantCulture), 238596054, 674038564, 1489),    //ok
                                    new transacao(6,DateTime.ParseExact("09/09/2023 14:18:20", "dd/MM/yyyy HH:mm:ss", CultureInfo.InvariantCulture), 573659065, 563856300, 49),      //ok
                                    new transacao(7,DateTime.ParseExact("09/09/2023 14:19:00", "dd/MM/yyyy HH:mm:ss", CultureInfo.InvariantCulture), 938485762, 2147483649, 44),     //NOK
                                    new transacao(8,DateTime.ParseExact("09/09/2023 14:19:01", "dd/MM/yyyy HH:mm:ss", CultureInfo.InvariantCulture), 573659065, 675869708, 150)      //ok

            };

            Array.Sort(TRANSACOES,
                delegate(transacao x, transacao y) { return x.data_transacao.CompareTo(y.data_transacao); });

            executarTransacaoFinanceira executor = new executarTransacaoFinanceira();
            foreach (transacao item in TRANSACOES)
            //Parallel.ForEach(TRANSACOES, item =>
            {
                //Console.WriteLine("datetime {0}", item.data_transacao);    
                executor.transferir(item.correlation_id, item.conta_origem, item.conta_destino, item.valor);
            };

        }
    }

    class executarTransacaoFinanceira: acessoDados
    {
        public void transferir(uint correlation_id, uint conta_origem, uint conta_destino, decimal valor)
        {
            contas_saldo conta_saldo_origem = getSaldo<contas_saldo>(conta_origem) ;
            //Console.WriteLine("Saldo conta origem {0}", conta_saldo_origem.saldo);
            if (conta_saldo_origem.saldo < valor)
            {
                Console.WriteLine("Transacao numero {0 } foi cancelada por falta de saldo", correlation_id);

            }
            else
            {
                contas_saldo conta_saldo_destino = getSaldo<contas_saldo>(conta_destino);
                //Console.WriteLine("Saldo conta destino {0}", conta_saldo_destino.saldo);
                conta_saldo_origem.saldo -= valor;
                conta_saldo_destino.saldo += valor;

                //Console.WriteLine(conta_saldo_origem.saldo);
                //Console.WriteLine(conta_saldo_destino.saldo);

                Console.WriteLine("Transacao numero {0} foi efetivada com sucesso! Novos saldos: Conta Origem:{1} | Conta Destino: {2}", correlation_id, conta_saldo_origem.saldo, conta_saldo_destino.saldo);
            }
        }
    }
    class contas_saldo
    {
        public contas_saldo(uint conta, decimal valor)
        {
            this.conta = conta;
            this.saldo = valor;
        }
        public uint conta { get; set; }
        public decimal saldo { get; set; }
    }
    class acessoDados
    {
        Dictionary<uint, decimal> SALDOS { get; set; }
        private List<contas_saldo> TABELA_SALDOS;
        public acessoDados()
        {
            TABELA_SALDOS = new List<contas_saldo>();
            TABELA_SALDOS.Add(new contas_saldo(938485762, 180));
            TABELA_SALDOS.Add(new contas_saldo(347586970, 1200));
            TABELA_SALDOS.Add(new contas_saldo(2147483649, 0));
            TABELA_SALDOS.Add(new contas_saldo(675869708, 4900));
            TABELA_SALDOS.Add(new contas_saldo(238596054, 478));
            TABELA_SALDOS.Add(new contas_saldo(573659065, 787));
            TABELA_SALDOS.Add(new contas_saldo(210385733, 10));
            TABELA_SALDOS.Add(new contas_saldo(674038564, 400));
            TABELA_SALDOS.Add(new contas_saldo(563856300, 1200));


            SALDOS = new Dictionary<uint, decimal>();
            this.SALDOS.Add(938485762, 180);
           
        }
        public T getSaldo<T>(uint id)
        {          
            return (T)Convert.ChangeType(TABELA_SALDOS.Find(x => x.conta == id), typeof(T));
        }
        public bool atualizar<T>(T  dado)
        {
            try
            {
                contas_saldo item = (dado as contas_saldo);
                TABELA_SALDOS.RemoveAll(x => x.conta == item.conta);
                TABELA_SALDOS.Add(dado as contas_saldo);
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return false;
            }
            
        }

    }
    
    class transacao
    {
        public transacao(uint correlation_id, DateTime data_transacao, uint conta_origem, uint conta_destino, decimal valor)
        {
            this.correlation_id = correlation_id;
            this.data_transacao = data_transacao;
            this.conta_origem = conta_origem;
            this.conta_destino = conta_destino;
            this.valor = valor;
        }
        public uint correlation_id { get; set; }

        public DateTime data_transacao { get; set; }
        public uint conta_origem { get; set; }
        public uint conta_destino { get; set; }
        public decimal valor { get; set; }
    }
}