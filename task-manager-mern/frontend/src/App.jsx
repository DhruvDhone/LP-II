import { useEffect, useState } from "react";
import axios from "axios";

const API_URL = "http://localhost:5000/api/tasks";

function App() {
  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [editId, setEditId] = useState(null);

  const fetchTasks = async () => {
    try {
      const response = await axios.get(API_URL);
      setTasks(response.data);
    } catch (error) {
      console.log("Error fetching tasks", error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (title.trim() === "") {
      alert("Please enter task title");
      return;
    }

    try {
      if (editId) {
        await axios.put(API_URL + "/" + editId, {
          title,
          description,
          status: "Updated"
        });
        setEditId(null);
      } else {
        await axios.post(API_URL, {
          title,
          description
        });
      }

      setTitle("");
      setDescription("");
      fetchTasks();
    } catch (error) {
      console.log("Error saving task", error);
    }
  };

  const editTask = (task) => {
    setTitle(task.title);
    setDescription(task.description);
    setEditId(task._id);
  };

  const deleteTask = async (id) => {
    try {
      await axios.delete(API_URL + "/" + id);
      fetchTasks();
    } catch (error) {
      console.log("Error deleting task", error);
    }
  };

  const completeTask = async (task) => {
    try {
      await axios.put(API_URL + "/" + task._id, {
        title: task.title,
        description: task.description,
        status: "Completed"
      });
      fetchTasks();
    } catch (error) {
      console.log("Error completing task", error);
    }
  };

  return (
    <div className="page">
      <div className="container">
        <h1>MERN Task Management System</h1>
        <p className="subtitle">
          Create, update, complete and delete daily tasks
        </p>

        <form className="task-form" onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Enter task title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />

          <textarea
            placeholder="Enter task description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          ></textarea>

          <button type="submit">
            {editId ? "Update Task" : "Add Task"}
          </button>
        </form>

        <div className="task-list">
          {tasks.length === 0 ? (
            <p className="empty">No tasks available</p>
          ) : (
            tasks.map((task) => (
              <div className="task-card" key={task._id}>
                <div>
                  <h2>{task.title}</h2>
                  <p>{task.description}</p>
                  <span className={task.status === "Completed" ? "done" : "pending"}>
                    {task.status}
                  </span>
                </div>

                <div className="buttons">
                  <button className="complete" onClick={() => completeTask(task)}>
                    Complete
                  </button>
                  <button className="edit" onClick={() => editTask(task)}>
                    Edit
                  </button>
                  <button className="delete" onClick={() => deleteTask(task._id)}>
                    Delete
                  </button>
                </div>
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
}

export default App;
