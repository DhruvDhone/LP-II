import { useEffect, useState } from "react";
import axios from "axios";

const API = "http://54.227.182.135:5000/api/posts";

function App() {
  const [posts, setPosts] = useState([]);

  const [form, setForm] = useState({
    title: "",
    content: "",
    author: ""
  });

  const fetchPosts = async () => {
    try {
      const response = await axios.get(API);
      setPosts(response.data);
    } catch (error) {
      console.log("Error fetching posts:", error);
    }
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.post(API, form);

      setForm({
        title: "",
        content: "",
        author: ""
      });

      fetchPosts();
    } catch (error) {
      console.log("Error creating post:", error);
    }
  };

  const deletePost = async (id) => {
    try {
      await axios.delete(`${API}/${id}`);
      fetchPosts();
    } catch (error) {
      console.log("Error deleting post:", error);
    }
  };

  return (
    <div className="container">
      <h1>Online Blog Application</h1>
      <p className="subtitle">Create, view and manage blog posts</p>

      <form className="form" onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter blog title"
          value={form.title}
          onChange={(e) =>
            setForm({
              ...form,
              title: e.target.value
            })
          }
          required
        />

        <input
          type="text"
          placeholder="Enter author name"
          value={form.author}
          onChange={(e) =>
            setForm({
              ...form,
              author: e.target.value
            })
          }
        />

        <textarea
          placeholder="Enter blog content"
          value={form.content}
          onChange={(e) =>
            setForm({
              ...form,
              content: e.target.value
            })
          }
          required
        ></textarea>

        <button type="submit">Create Post</button>
      </form>

      <div className="posts">
        {posts.length === 0 ? (
          <p className="empty">No blog posts found</p>
        ) : (
          posts.map((post) => (
            <div className="card" key={post._id}>
              <h2>{post.title}</h2>
              <p className="author">
                <strong>Author:</strong> {post.author || "Admin"}
              </p>
              <p>{post.content}</p>
              <button className="delete" onClick={() => deletePost(post._id)}>
                Delete
              </button>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default App;
