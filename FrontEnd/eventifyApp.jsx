import React, { useState } from "react";
import axios from "axios";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Tabs, TabsList, TabsTrigger, TabsContent } from "@/components/ui/tabs";

export default function EventifyApp() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [fullName, setFullName] = useState("");
  const [token, setToken] = useState(localStorage.getItem("jwt") || "");
  const [eventData, setEventData] = useState({
    name: "",
    location: "",
    datetime: "",
    price: "",
  });

  const handleLogin = async () => {
    try {
      const response = await axios.post("http://localhost:8081/api/auth/login", {
        email,
        password,
      });
      localStorage.setItem("jwt", response.data);
      setToken(response.data);
      alert("Login successful");
    } catch (err) {
      alert("Login failed");
    }
  };

  const handleSignup = async () => {
    try {
      await axios.post("http://localhost:8081/api/auth/register", {
        email,
        password,
        fullName,
      });
      alert("Signup successful");
    } catch (err) {
      alert("Signup failed");
    }
  };

  const handleEventCreate = async () => {
    try {
      await axios.post("http://localhost:8082/api/events", eventData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      alert("Event created");
    } catch (err) {
      alert("Event creation failed");
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-5xl mx-auto">
        <h1 className="text-4xl font-bold mb-6 text-center">Eventify - Smart Event Management</h1>

        <Tabs defaultValue="login" className="mb-6">
          <TabsList className="flex justify-center">
            <TabsTrigger value="login">Login</TabsTrigger>
            <TabsTrigger value="signup">Signup</TabsTrigger>
            <TabsTrigger value="createEvent">Create Event</TabsTrigger>
            <TabsTrigger value="payment">Payment</TabsTrigger>
          </TabsList>

          {/* Login Tab */}
          <TabsContent value="login">
            <Card>
              <CardContent className="space-y-4 pt-6">
                <Input placeholder="Email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                <Input placeholder="Password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                <Button className="w-full" onClick={handleLogin}>Login</Button>
              </CardContent>
            </Card>
          </TabsContent>

          {/* Signup Tab */}
          <TabsContent value="signup">
            <Card>
              <CardContent className="space-y-4 pt-6">
                <Input placeholder="Full Name" value={fullName} onChange={(e) => setFullName(e.target.value)} />
                <Input placeholder="Email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                <Input placeholder="Password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                <Button className="w-full" onClick={handleSignup}>Create Account</Button>
              </CardContent>
            </Card>
          </TabsContent>

          {/* Create Event Tab */}
          <TabsContent value="createEvent">
            <Card>
              <CardContent className="space-y-4 pt-6">
                <Input placeholder="Event Name" value={eventData.name} onChange={(e) => setEventData({ ...eventData, name: e.target.value })} />
                <Input placeholder="Location" value={eventData.location} onChange={(e) => setEventData({ ...eventData, location: e.target.value })} />
                <Input placeholder="Date & Time" type="datetime-local" value={eventData.datetime} onChange={(e) => setEventData({ ...eventData, datetime: e.target.value })} />
                <Input placeholder="Ticket Price" type="number" value={eventData.price} onChange={(e) => setEventData({ ...eventData, price: e.target.value })} />
                <Button className="w-full" onClick={handleEventCreate}>Create Event</Button>
              </CardContent>
            </Card>
          </TabsContent>

          {/* Payment Tab */}
          <TabsContent value="payment">
            <Card>
              <CardContent className="space-y-4 pt-6">
                <Input placeholder="Card Number" type="text" />
                <Input placeholder="Expiry (MM/YY)" />
                <Input placeholder="CVV" type="password" />
                <Button className="w-full">Pay</Button>
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </div>
    </div>
  );
}
