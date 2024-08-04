// src/components/AddStudent.js

import React, { useState } from 'react';
import axios from 'axios';

const AddStudent = () => {
    const [name, setName] = useState('');
    const [level, setLevel] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        const student = { name, level };
        axios.post('http://localhost:8080/student/add', student)
            .then(response => {
                alert(response.data);
                setName('');
                setLevel('');
            })
            .catch(error => {
                console.error('There was an error adding the student!', error);
            });
    }

    return (
        <div>
            <h2>Add Student</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name: </label>
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
                </div>
                <div>
                    <label>Level: </label>
                    <input type="text" value={level} onChange={(e) => setLevel(e.target.value)} required />
                </div>
                <button type="submit">Add Student</button>
            </form>
        </div>
    );
}

export default AddStudent;
