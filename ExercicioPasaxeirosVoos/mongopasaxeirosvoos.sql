use internacional
db.pasaxeiros.remove({})
db.voos.remove({})
var pasaxeiros=
[
{_id:"361a" ,nome:"luis",telf: "9861a" ,cidade: "vigo",nreservas:0.0},
{_id:"362b" ,nome: "ana",telf:"9861b" ,cidade: "lugo",nreservas: 0.0},
{_id:"363c" ,nome: "pedro",telf: "9861c" ,cidade:"lugo",nreservas:0.0},
{_id:"364d" ,nome: "ana",telf:"9861d" ,cidade: "vigo",nreservas:0.0}
]
var voos=
[
{_id:1.0,orixe:"vigo",destino:"estambul",prezo:150.0},
{_id:2.0,orixe:"estambul",destino:"vigo",prezo:200.0},
{_id:3.0,orixe:"vigo",destino:"londres",prezo:80.0},
{_id:4.0,orixe:"londres",destino:"vigo",prezo:90.0},
{_id:5.0,orixe:"vigo",destino:"lisboa",prezo:90.0},
{_id:6.0,orixe:"lisboa",destino:"vigo",prezo:100.0},
{_id:7.0,orixe:"vigo",destino:"viena",prezo:200.0},
{_id:8.0,orixe:"viena",destino:"vigo",prezo:250.0},
{_id:9.0,orixe:"vigo",destino:"tunez",prezo:160.0},
{_id:10.0,orixe:"tunez",destino:"vigo",prezo:150.0},
{_id:11.0,orixe:"vigo",destino:"paris",prezo:200.0},
{_id:12.0,orixe:"paris",destino:"vigo",prezo:90.0}
]
db.pasaxeiros.insert(pasaxeiros)
db.voos.insert(voos)

