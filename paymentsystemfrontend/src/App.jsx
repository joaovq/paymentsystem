import axios from 'axios'
import { useEffect, useState } from 'react'

const api = axios.create({ baseURL: "http://localhost:8080/" })

function App() {
  const [transactions, setTransactions] = useState([])
  const [file, setFile] = useState(null)

  const handleFileChange = e => {
    const file = e.target.files[0]
    setFile(file)
  }

  const uploadFile = async () => {
    const formData = new FormData()
    formData.append('file', file)
    let response = await api.post("cnab/upload", formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    console.log(response.data)
  }

  const fetchTransactions = async () => {
    const response = await api.get("transactions")
    console.log(response.data)
    setTransactions(response.data)
  }

  useEffect(() => { fetchTransactions() }, [])

  return (
    <>
      <div>
        <h1 className='text-3xl px-4'>Importação de CNAB</h1>
      </div>
      <div>
        <label htmlFor="file">Choose file</label>
        <input type="file" accept='.txt' name='file' id='file' onChange={handleFileChange} />
        <button onClick={uploadFile}>Upload file</button>
      </div>
      <div>
        <h2>Transações</h2>
        <ul>
          {transactions.map((report, key) => (
            <>
              <li>
                <table>
                  <thead>
                    <tr>
                      <td>Cartão</td>
                      <td>Cpf</td>
                      <td>Data</td>
                      <td>Dono da loja</td>
                      <td>Hora</td>
                      <td>Nome da loja</td>
                      <td>Tipo</td>
                      <td>Valor</td>
                    </tr>
                  </thead>
                  <tbody>
                    {report.transactions.map((transaction,key) => (
                      <>
                        <tr>
                          <td>{transaction.card}</td>
                          <td>{transaction.cpf}</td>
                          <td>{transaction.date}</td>
                          <td>{transaction.ownerStore}</td>
                          <td>{transaction.time}</td>
                          <td>{transaction.nameStore}</td>
                          <td>{transaction.type}</td>
                          <td>{transaction.value}</td>
                        </tr>
                      </>
                    ))}
                  </tbody>
                </table>
              </li>
            </>
          ))}
        </ul>
      </div>
    </>
  )
}

export default App
