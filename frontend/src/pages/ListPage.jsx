import React, { useEffect, useState } from "react";
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

  const handleSubmit = async () => {

    if (!policyInput || !name || !status) {
      alert("Please fill all fields");
      return;
    }

    const policyData = {
      input: policyInput,
      name: name,
      status: status
    };

    try {

      if (editId) {

        await api.put(`/policies/${editId}`, policyData);

        alert("Policy Updated Successfully");

      } else {

        await api.post("/policies", policyData);

        alert("Policy Added Successfully");
      }

      fetchPolicies();

      setPolicyInput("");
      setName("");
      setStatus("");
      setEditId(null);

    } catch (error) {
      console.log(error);
    }
  };

  const handleDelete = async (id) => {

    const confirmDelete = window.confirm(
      "Are you sure you want to delete?"
    );

    if (!confirmDelete) return;

    try {

      await api.delete(`/policies/${id}`);

      alert("Policy Deleted Successfully");

      fetchPolicies();

    } catch (error) {
      console.log(error);
    }
  };

  const handleEdit = (policy) => {

    setEditId(policy.id);
    setPolicyInput(policy.input);
    setName(policy.name);
    setStatus(policy.status);
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

      <div className="form-container">

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

        <select
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        >
          <option value="">Select Status</option>
          <option value="approved">approved</option>
          <option value="pending">pending</option>
          <option value="rejected">rejected</option>
        </select>

        <button onClick={handleSubmit}>
          {editId ? "Update Policy" : "Add Policy"}
        </button>

      </div>

      <input
        type="text"
        className="search-box"
        placeholder="Search Policy..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <table>

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
                style={{
                  color:
                    policy.status?.toLowerCase() === "approved"
                      ? "green"
                      : policy.status?.toLowerCase() === "rejected"
                      ? "red"
                      : "orange",
                  fontWeight: "bold",
                }}
              >
                {policy.status}
              </td>

              <td>

                <button
                  className="edit-btn"
                  onClick={() => handleEdit(policy)}
                >
                  Edit
                </button>

                <button
                  className="delete-btn"
                  onClick={() => handleDelete(policy.id)}
                >
                  Delete
                </button>

              </td>

            </tr>

          ))}

        </tbody>

      </table>

      <h3>
        Total Policies: {filteredPolicies.length}
      </h3>

      <p className="footer">
        Policy Compliance Testing System © 2026
      </p>

    </div>
  );
}

export default ListPage;