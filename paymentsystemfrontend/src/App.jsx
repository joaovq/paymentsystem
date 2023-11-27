import axios from 'axios'
import { useEffect, useState } from 'react'
import { Tb360 } from 'react-icons/tb';

const api = axios.create({ baseURL: import.meta.env.VITE_REACT_APP_BASE_URL })

function App() {
  const [transactions, setTransactions] = useState([])
  const [file, setFile] = useState(null)
  const [message, setMessage] = useState("")
  const [isLoading, setLoading] = useState(false)

  const handleFileChange = e => {
    const file = e.target.files[0]
    setFile(file)
  }

  const uploadFile = async () => {
    try {
      const formData = new FormData()
      formData.append('file', file)
      let response = await api.post("cnab/upload", formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).catch(reason => {
        console.log(reason)
      })
      setMessage(response.data)
    } catch (error) {
      console.log(error)
    }
  }

  const fetchTransactions = async () => {
    try {
      setLoading(true)
      const response = await api.get("transactions")
      console.log(response.data)
      setTransactions(response.data)
    } catch (error) {
      console.error(error)
    } finally {
      setLoading(false)
    }
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
            <button onClick={uploadFile} className='bg-indigo-500 text-white px-5 py-2 shadow-md rounded-lg'>Upload file</button>
          </div>
          <div>
            <button onClick={fetchTransactions} className='bg-gray-400 text-white shadow-md px-5 py-2 rounded-lg'><span className='flex flex-row gap-1 content-center justify-center'><Tb360 />Refresh transactions</span></button>
          </div>
          <div>
            {message}
          </div>
        </div>
        <div>
          <h1 className='text-2xl font-semibold'>Transações</h1>
          <ul>
            {isLoading ? <><p className='py-6'>Processing...</p>
              </> : transactions.map((report, key) => (
                <>
                  <li>
                    <table className='table-auto'>
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
