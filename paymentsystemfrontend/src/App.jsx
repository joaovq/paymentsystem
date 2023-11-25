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
      <main className='p-5'>
      <div>
        <h1 className=' text-4xl'>Importação de CNAB</h1>
      </div>
      <div className='flex flex-col gap-10 p-4 py-8'>
        <div className='flex flex-col gap-5'>
          <label htmlFor="file">Choose file</label>
          <input type="file" accept='.txt' name='file' id='file' onChange={handleFileChange} />
        </div>
        <div>
          <button onClick={uploadFile} className='bg-green-700 text-white px-5 py-2 rounded-lg'>Upload file</button>
        </div>
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
                    {report.transactions.map((transaction, key) => (
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
      </main>
    </>
  )
}

export default App
