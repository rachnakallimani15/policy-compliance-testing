import { useEffect, useState } from "react";
import api from "../services/api";

function ListPage() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get("/policy-checks")
      .then((res) => {
        setItems(res.data);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  if (loading) return <h2>Loading...</h2>;
  if (items.length === 0) return <h2>No data available</h2>;

  return (
    <div>
      <h1>Policy List</h1>
      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Policy Name</th>
            <th>Status</th>
          </tr>
        </thead>

        <tbody>
          {items.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.policyName}</td>
              <td>{item.complianceStatus}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ListPage;