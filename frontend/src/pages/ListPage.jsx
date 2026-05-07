import { useEffect, useState } from "react";
import api from "../services/api";
import "./ListPage.css";

function ListPage() {

  const [policies, setPolicies] = useState([]);

  const [policyInput, setPolicyInput] = useState("");
  const [name, setName] = useState("");
  const [status, setStatus] = useState("");

  const [search, setSearch] = useState("");

  const [editId, setEditId] = useState(null);

  useEffect(() => {
    fetchPolicies();
  }, []);

  const fetchPolicies = async () => {
    try {
      const response = await api.get("/policies");
      setPolicies(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const addPolicy = async () => {

    if (
      policyInput.trim() === "" ||
      name.trim() === "" ||
      status.trim() === ""
    ) {
      alert("Please fill all fields");
      return;
    }

    const newPolicy = {
      input: policyInput,
      name: name,
      status: status,
    };

    try {

      if (editId) {

        await api.put(`/policies/${editId}`, newPolicy);

        alert("Policy Updated Successfully");

        setEditId(null);

      } else {

        await api.post("/policies", newPolicy);

        alert("Policy Added Successfully");
      }

      fetchPolicies();

      setPolicyInput("");
      setName("");
      setStatus("");

    } catch (error) {
      console.log(error);
      alert("Operation Failed");
    }
  };

  const deletePolicy = async (id) => {

    try {

      await api.delete(`/policies/${id}`);

      alert("Deleted Successfully");

      fetchPolicies();

    } catch (error) {

      console.log(error);

      alert("Delete Failed");
    }
  };

  const editPolicy = (policy) => {

    setPolicyInput(policy.input);
    setName(policy.name);
    setStatus(policy.status);

    setEditId(policy.id);
  };

  const filteredPolicies = policies.filter((policy) =>
    policy.name.toLowerCase().includes(search.toLowerCase()) ||
    policy.input.toLowerCase().includes(search.toLowerCase()) ||
    policy.status.toLowerCase().includes(search.toLowerCase())
  );

  return (

    <div className="container">

      <h1 className="title">Policy List</h1>

      <p className="subtitle">
        Manage and monitor all your policies in one place
      </p>

      {/* FORM */}

      <div className="form-box">

        <input
          type="text"
          placeholder="Policy Input"
          value={policyInput}
          onChange={(e) => setPolicyInput(e.target.value)}
        />

        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <input
          type="text"
          placeholder="Status"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        />

        <button onClick={addPolicy}>
          {editId ? "Update" : "Add Policy"}
        </button>

      </div>

      {/* SEARCH */}

      <input
        type="text"
        className="search-box"
        placeholder="Search Policy..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      {/* TABLE */}

      <table className="policy-table">

        <thead>
          <tr>
            <th>ID</th>
            <th>Policy Input</th>
            <th>Name</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>

          {filteredPolicies.map((policy) => (

            <tr key={policy.id}>

              <td>{policy.id}</td>

              <td>{policy.input}</td>

              <td>{policy.name}</td>

              <td
                className={
                  policy.status.toLowerCase() === "approved"
                    ? "approved"
                    : "pending"
                }
              >
                {policy.status}
              </td>

              <td>

                <button
                  className="edit-btn"
                  onClick={() => editPolicy(policy)}
                >
                  Edit
                </button>

                <button
                  className="delete-btn"
                  onClick={() => deletePolicy(policy.id)}
                >
                  Delete
                </button>

              </td>

            </tr>

          ))}

        </tbody>

      </table>

      <div className="count-box">
        Total Policies: {filteredPolicies.length}
      </div>

    </div>
  );
}

export default ListPage;